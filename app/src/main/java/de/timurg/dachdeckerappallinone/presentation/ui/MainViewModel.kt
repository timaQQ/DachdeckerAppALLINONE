package de.timurg.dachdeckerappallinone.presentation.ui

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import de.timurg.dachdeckerappallinone.data.CalculationsData
import de.timurg.dachdeckerappallinone.data.ProductsData
import de.timurg.dachdeckerappallinone.data.remote.ProductApi
import de.timurg.dachdeckerappallinone.domain.model.Calculation
import de.timurg.dachdeckerappallinone.domain.model.Project
import de.timurg.dachdeckerappallinone.domain.model.User
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _projectsList = MutableLiveData<MutableList<Project>>()
    val projectsList: LiveData<MutableList<Project>>
        get() = _projectsList

    private var _calculationsListFP = MutableLiveData<MutableList<Calculation>>()
    val calculationsListFP: LiveData<MutableList<Calculation>>
        get() = _calculationsListFP

    val calcData = CalculationsData()
    val calcItems = calcData.items
    val calcItemsList = CalculationsData().calculationsList
    fun loadProducts() {
        viewModelScope.launch {
            try {
                calcData.getItem()
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "failed loading products: $e")
            }
        }
    }
    val productsApiRepository = ProductsData(ProductApi)

    val products = productsApiRepository.products

    fun loadProductsApi() {
        viewModelScope.launch {
            try {
                productsApiRepository.getProduct()
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "failed loading products: $e")
            }
        }
    }

    fun logIn(email: String, key: String) {
        firebaseAuth.signInWithEmailAndPassword(email, key)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _currentUser.value = firebaseAuth.currentUser
                    getUserData()
                    _toast.value = "Willkommen zurück, ${user.value?.name} !"
                    _toast.value = null
                } else {
                    Log.e(TAG, "Failed to login: ${it.exception?.message}")
                    _toast.value = it.exception?.message
                    _toast.value = null
                }
            }
    }

    fun signIn(email: String, key: String, user: User) {
        firebaseAuth.createUserWithEmailAndPassword(email, key)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseAuth.signInWithEmailAndPassword(email, key).addOnCompleteListener {
                        if (it.isSuccessful) {
                            _currentUser.value = firebaseAuth.currentUser
                            setName(user)
                            _toast.value = "Willkommen ${user.name}"
                            _toast.value = null
                        } else {
                            Log.e(TAG, "Failed to login: ${it.exception?.message}")
                            _toast.value = it.exception?.message
                            _toast.value = null
                        }
                    }
                } else {
                    Log.e(TAG, "Failed to signin: ${it.exception?.message}")
                    _toast.value = it.exception?.message
                    _toast.value = null
                }
            }
    }

    fun logOut() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

    fun setName(user: User) {
        db.collection("user").document(currentUser.value!!.uid).set(user)
            .addOnFailureListener {
                Log.e(TAG, "Failed to write document: $it")
                _toast.value = "Failed to create user."
                _toast.value = null
            }
    }

    fun getUserData() {
        db.collection("user").document(currentUser.value!!.uid)
            .get().addOnSuccessListener {
                _user.value = it.toObject(User::class.java)
            }
            .addOnFailureListener {
                Log.e(TAG, "Failed to read user data: $it")
            }
    }

    fun getProjects() {
        db.collection("user").document(currentUser.value!!.uid).collection("projects")
            .addSnapshotListener { snapshot, e ->
                if (e != null){
                    Log.e(TAG, "Failed to get Projects: $e")
                } else {
                    if (snapshot != null) {
                        _projectsList.value = snapshot.toObjects(Project::class.java)
                    }
                }
            }
    }

    fun createProject(project: Project, uri: Uri? = null) {
        db.collection("user").document(currentUser.value!!.uid).collection("projects")
            .add(project).addOnFailureListener {
                Log.e(TAG, "Failed to create user data: $it")
            }
        if (uri != null) {
            uploadImage(uri, project.title)
        }
    }

    fun deleteProject(id: String){
        db.collection("user").document(currentUser.value!!.uid)
            .collection("projects").document(id)
            .delete().addOnFailureListener {
                Log.e(TAG, "Failed to delete project: $it")
            }
    }

    fun uploadCalculation(calculation: Calculation, project: Project){
        db.collection("user").document(currentUser.value!!.uid)
            .collection("projects").document(project.id)
            .collection("calculations")
            .add(calculation).addOnFailureListener {
                Log.e(TAG, "Failed to upload calculation: $it")
            }
    }

//    fun getCalculationsFromProject(project: Project){
//        db.collection("user").document(currentUser.value!!.uid).collection("projects")
//            .document(project.id).collection("calculations")
//            .get().addOnSuccessListener {
//                var cList = mutableListOf<Calculation>()
//                for (documentSnapshot in it.documents){
//                    val cal = documentSnapshot.toObject(Calculation::class.java)
//                    if (cal != null){
//                        cList.add(cal)
//                    }
//                }
//                _calculationsListFP.value = cList
//            }.addOnFailureListener {
//                Log.e(TAG, "Failed to get calculation data from project: $it")
//            }
//
//
//    }

    fun uploadImage(uri: Uri, projectTitle: String) {
        val imageRef = storageRef.child(
            "images/${currentUser.value?.uid}/projects/${
                db.collection("user")
                    .document(currentUser.value!!.uid).collection("projects")
                    .whereArrayContains("title", projectTitle)
            }"
        )

        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnFailureListener {
            Log.e(TAG, "Failed to upload image: $it")
        }
        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.e(TAG, "downloadUrl: $it")
                }
            }
        }
    }


}

enum class ApiStatus { LOADING, ERROR, DONE }
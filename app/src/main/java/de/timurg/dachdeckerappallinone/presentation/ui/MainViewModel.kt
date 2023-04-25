package de.timurg.dachdeckerappallinone.presentation.ui

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import de.timurg.dachdeckerappallinone.domain.model.User

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun logIn(email: String, key: String) {
        firebaseAuth.signInWithEmailAndPassword(email, key)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _currentUser.value = firebaseAuth.currentUser
                    _toast.value = "Willkommen zurück, ${user.value?.name} !"
                    _toast.value = null
                } else {
                    Log.e(ContentValues.TAG, "Failed to login: ${it.exception?.message}")
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
//                            setName(user)
                            _toast.value = "Willkommen ${user.name}"
                            _toast.value = null
                        } else {
                            Log.e(ContentValues.TAG, "Failed to login: ${it.exception?.message}")
                            _toast.value = it.exception?.message
                            _toast.value = null
                        }
                    }
                } else {
                    Log.e(ContentValues.TAG, "Failed to signin: ${it.exception?.message}")
                    _toast.value = it.exception?.message
                    _toast.value = null
                }
            }
    }

    fun logOut() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

    fun getUserData() {
        db.collection("user").document(currentUser.value!!.uid)
            .get().addOnSuccessListener {
                _user.value = it.toObject(User::class.java)
            }
            .addOnFailureListener {
                Log.e(ContentValues.TAG, "Failed to read user data: $it")
            }
    }


}
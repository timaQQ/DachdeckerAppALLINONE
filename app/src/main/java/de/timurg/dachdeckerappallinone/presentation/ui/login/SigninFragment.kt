package de.timurg.dachdeckerappallinone.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.databinding.FragmentSigninBinding
import de.timurg.dachdeckerappallinone.domain.model.User
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel


class SigninFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentSigninBinding? = null
    private val binding: FragmentSigninBinding
        get() = _binding ?: throw RuntimeException("FragmentSigninBinding == null")

    private var mailEmpty = MutableLiveData(true)
    private var passwordEmpty = MutableLiveData(true)
    private var passwordTwoEmpty = MutableLiveData(true)
    private var nameEmpty = MutableLiveData(true)
    private var unchecked = MutableLiveData(true)
    private var buttonDisabled = MutableLiveData(true)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
        val registerButton = binding.registerButtonSignin

        with(binding) {
            signinEmailEdittext.addTextChangedListener {
                mailEmpty.value = it.toString().isNullOrEmpty()
            }

            signinPasswordEdittext.addTextChangedListener {
                passwordEmpty.value = it.toString().isNullOrEmpty()
            }

            signinPasswordEdittextTwo.addTextChangedListener {
                passwordTwoEmpty.value = it.toString().isNullOrEmpty()
            }

            signinNameEdittext.addTextChangedListener {
                nameEmpty.value = it.toString().isNullOrEmpty()
            }

            registerButtonSignin.setOnClickListener {
                signIn()
            }

            cancelButtonSignin.setOnClickListener {
                findNavController().navigateUp()
            }


            checkBox.setOnClickListener {
                if (binding.checkBox.isChecked()) {
                    unchecked.value = false
                }
                if (!binding.checkBox.isChecked()) {
                    unchecked.value = true
                }
            }

        }

        with(viewModel) {
            currentUser.observe(viewLifecycleOwner) {
                if (it != null) {
                    findNavController().navigate(R.id.mainFragment)
                }
            }

            toast.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }


        mailEmpty.observe(viewLifecycleOwner) {
            checkButtonDisabled(
                mailEmpty.value!!,
                passwordEmpty.value!!,
                passwordTwoEmpty.value!!,
                nameEmpty.value!!,
                unchecked.value!!
            )
        }
        passwordEmpty.observe(viewLifecycleOwner) {
            checkButtonDisabled(
                mailEmpty.value!!,
                passwordEmpty.value!!,
                passwordTwoEmpty.value!!,
                nameEmpty.value!!,
                unchecked.value!!
            )
        }
        passwordTwoEmpty.observe(viewLifecycleOwner) {
            checkButtonDisabled(
                mailEmpty.value!!,
                passwordEmpty.value!!,
                passwordTwoEmpty.value!!,
                nameEmpty.value!!,
                unchecked.value!!
            )
        }
        nameEmpty.observe(viewLifecycleOwner) {
            checkButtonDisabled(
                mailEmpty.value!!,
                passwordEmpty.value!!,
                passwordTwoEmpty.value!!,
                nameEmpty.value!!,
                unchecked.value!!
            )
        }
        unchecked.observe(viewLifecycleOwner) {
            checkButtonDisabled(
                mailEmpty.value!!,
                passwordEmpty.value!!,
                passwordTwoEmpty.value!!,
                nameEmpty.value!!,
                unchecked.value!!
            )
        }

        buttonDisabled.observe(viewLifecycleOwner) {
            if (it) {
                registerButton.isEnabled = false
                registerButton.setBackgroundColor(
                    ContextCompat.getColor(
                        registerButton.context,
                        R.color.text_grey
                    )
                )

            } else {
                registerButton.isEnabled = true
                registerButton.setBackgroundColor(
                    ContextCompat.getColor(
                        registerButton.context,
                        R.color.button_red
                    )
                )
            }
        }



    }

    private fun checkButtonDisabled(
        mailValue: Boolean,
        passwordValue: Boolean,
        passwordTwoValue: Boolean,
        nameValue: Boolean,
        unchecked: Boolean
    ) {
        buttonDisabled.value =
            mailValue || passwordValue || passwordTwoValue || nameValue || unchecked
    }

    private fun signIn() {
        val mail = binding.signinEmailEdittext.text.toString()
        val key = binding.signinPasswordEdittext.text.toString()
        val name = binding.signinNameEdittext.text.toString()

        if (!mail.isNullOrEmpty() && !key.isNullOrEmpty() && !name.isNullOrEmpty()) {
            val newUser = User(name = name)
            viewModel.signIn(mail, key, newUser)
        }

    }
}
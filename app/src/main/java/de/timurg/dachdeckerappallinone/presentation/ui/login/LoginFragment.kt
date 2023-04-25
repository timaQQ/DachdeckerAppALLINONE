package de.timurg.dachdeckerappallinone.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.databinding.FragmentLoginBinding
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            toast.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }

            currentUser.observe(viewLifecycleOwner) {
                if (it != null) {
                    findNavController().navigate(R.id.mainFragment)
                }
            }
        }


        with(binding) {
            loginButton.setOnClickListener {
                val email = binding.loginEmailEdittext.text.toString()
                val password = binding.loginPasswordEdittext.text.toString()

                logoImage.setImageResource(R.drawable.logo_placeholder)

                if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                    viewModel.logIn(email, password)
                }
            }

            registerButtonSignin.setOnClickListener {
                findNavController().navigate(R.id.signinFragment)
            }
        }
    }
}
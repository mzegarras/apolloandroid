package cloud.csonic.apollodemo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cloud.csonic.apollodemo.databinding.FragmentFirstBinding
import cloud.csonic.apollodemo.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val viewModel by viewModels<CustomerViewModel>()
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        setUpObservers()

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            (activity as BaseActivity).showLoading()
            viewModel.loadCustomers(_binding?.txtName?.text.toString())
        }





    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI() {

    }

    private fun setUpObservers() {
        viewModel.getCustomer().observe(viewLifecycleOwner) { customerList ->
            customerList?.let {
                (activity as BaseActivity).hideLoading()

                _binding?.textviewFirst?.text = context?.getString(R.string.cic_title, "${it.cic}");

                _binding?.textviewDocument?.text = context?.getString(R.string.document_title,
                    "${it.document.type}",
                    "${it.document.number}");

                _binding?.textviewName?.text = context?.getString(R.string.document_name,
                    "${it.name}",
                    "${it.lastName}");
                _binding?.textviewMail?.text = context?.getString(R.string.document_mail, "${it.mail}");
                _binding?.textviewGender?.text = context?.getString(R.string.document_genero, "${it.gender}");
                _binding?.textviewAddress?.text = context?.getString(R.string.document_address, "${it.addresses?.size}");
                _binding?.textviewPhones?.text = context?.getString(R.string.document_phones, "${it.phones?.size}");
            }
        }

    }


}
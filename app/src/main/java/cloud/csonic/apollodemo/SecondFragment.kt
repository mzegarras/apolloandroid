package cloud.csonic.apollodemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cloud.csonic.apollodemo.databinding.FragmentSecondBinding
import cloud.csonic.apollodemo.viewmodel.MainViewModel
//import cloud.csonic.apollodemo.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        setUpObservers()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        (activity as BaseActivity).showLoading()
        mainViewModel.loadAccounts();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI() {

    }

    private fun setUpObservers() {
        mainViewModel.getAccounts().observe(viewLifecycleOwner,{accountList ->
            accountList?.let {
                (activity as BaseActivity).hideLoading()
                Log.d(SecondFragment::class.qualifiedName,"data----${it.size.toString()}");
            }
        })
    }
}
package com.d3if0028.hitungbmi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.d3if0028.hitungbmi.R
import com.d3if0028.hitungbmi.data.KategoriBmi
import com.d3if0028.hitungbmi.ui.SaranFragmentArgs
import com.d3if0028.hitungbmi.databinding.FragmentSaranBinding

class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaranBinding.inflate(
            layoutInflater,container,false
        )
        updateUI(args.kategori)
        return binding.root
    }

    // Membuat Data untuk menampilkan saran
    private fun updateUI(kategori: KategoriBmi) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when(kategori){
            KategoriBmi.KURUS -> {
                actionBar?.title = getString(R.string.kurus)
                binding.ivSaran.setImageResource(R.drawable.kurus)
                binding.tvSaran.text = getString(R.string.saran_kurus)
            }
            KategoriBmi.IDEAL -> {
                actionBar?.title = getString(R.string.ideal)
                binding.ivSaran.setImageResource(R.drawable.ideal)
                binding.tvSaran.text = getString(R.string.saran_ideal)
            }
            KategoriBmi.GEMUK -> {
                actionBar?.title = getString(R.string.gemuk)
                binding.ivSaran.setImageResource(R.drawable.gemuk)
                binding.tvSaran.text = getString(R.string.saran_gemuk)
            }
        }
    }
}
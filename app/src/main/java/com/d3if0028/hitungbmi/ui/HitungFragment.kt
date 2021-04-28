 package com.d3if0028.hitungbmi.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.d3if0028.hitungbmi.R
import com.d3if0028.hitungbmi.data.KategoriBmi
import com.d3if0028.hitungbmi.databinding.FragmentHitungBinding


class HitungFragment : Fragment() {
    private val viewModel: HitungViewModel by viewModels()
    private lateinit var binding: FragmentHitungBinding
    private lateinit var kategoriBmi: KategoriBmi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(
                layoutInflater ,container,false)
        binding.btnHitung.setOnClickListener{
            hitungBmi()
        }
        binding.btnReset.setOnClickListener{
            resetBmi()
        }
        binding.btnSaran.setOnClickListener {view : View ->
            view.findNavController().navigate(
                HitungFragmentDirections. actionHitungFragmentToSaranFragment(kategoriBmi)
            )
        }
        binding.btnShare.setOnClickListener { shareData() }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHasilBmi().observe(viewLifecycleOwner,{
            if (it == null) return@observe
            binding.tvTitleBMI.text = getString(R.string.bmi_x, it.bmi)
            binding.tvKategori.text = getString(R.string.kategori_x, getKategori(it.kategori))
            binding.btnGroup.visibility = View.VISIBLE
        })

    }

    private fun shareData() {
        val selectedID = binding.radioGroup.checkedRadioButtonId
        val gender = if (selectedID == R.id.rb_pria)
            getString(R.string.pria)
        else
            getString(R.string.wanita)

        val message = getString(R.string.bagikan_template,
                binding.etBerat.text,
                binding.etTinggi.text,
                gender,
                binding.tvTitleBMI.text,
                binding.tvKategori.text
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun resetBmi() {
        binding.etBerat.requestFocus()
        binding.etBerat.text.clear()
        binding.etTinggi.text.clear()
        binding.rbPria.isChecked = false
        binding.rbWanita.isChecked = false
    }

    private fun hitungBmi() {
        val berat = binding.etBerat.text.toString()
        if (TextUtils.isEmpty(berat)){
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_SHORT).show()
            return
        }

        val tinggi = binding.etTinggi.text.toString()
        if (TextUtils.isEmpty(tinggi)){
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_SHORT).show()
            return
        }


        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_SHORT).show()
            return
        }

        val isMale = selectedId == R.id.rb_pria
        viewModel.hitungBmi(berat, tinggi, isMale)

    }
    // Memngambil data dari kategori
    private fun getKategori(kategori: KategoriBmi): String {
        val stringRes = when (kategori) {
            KategoriBmi.KURUS -> R.string.kurus
            KategoriBmi.IDEAL -> R.string.ideal
            KategoriBmi.GEMUK -> R.string.gemuk
        }
        return getString(stringRes)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_about){
            findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
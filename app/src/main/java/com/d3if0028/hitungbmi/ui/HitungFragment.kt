 package com.d3if0028.hitungbmi.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.d3if0028.hitungbmi.R
import com.d3if0028.hitungbmi.databinding.FragmentHitungBinding


class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding
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
                R.id.action_hitungFragment_to_saranFragment
            )
        }
        return binding.root
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
        val tinggiCm = tinggi.toFloat()/100

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_SHORT).show()
            return
        }

        val isMale = selectedId == R.id.rb_pria
        val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
        val kategori = getKategori(bmi,isMale)

        binding.tvTitleBMI.text = getString(R.string.bmi_x, bmi)
        binding.tvKategori.text = getString(R.string.kategori_x,kategori)
        binding.btnSaran.visibility = View.VISIBLE

    }
    // Memngambil data dari kategori
    private fun getKategori(bmi: Float, isMale: Boolean): String {
        val stringRes = if(isMale){
            when{
                bmi < 20.5 -> R.string.kurus
                bmi >= 27.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        }else{
            when{
                bmi < 18.5 -> R.string.kurus
                bmi >= 25.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        }
        return getString(stringRes)
    }

}
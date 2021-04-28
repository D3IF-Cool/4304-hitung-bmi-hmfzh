package com.d3if0028.hitungbmi.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if0028.hitungbmi.R
import com.d3if0028.hitungbmi.data.HitungBmi
import com.d3if0028.hitungbmi.databinding.ItemHistoriBinding
import com.d3if0028.hitungbmi.db.BmiEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : RecyclerView.Adapter<HistoriAdapter.ViewHolder>() {
    class ViewHolder( private val binding: ItemHistoriBinding ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: BmiEntity) = with(binding) {
            tvTanggal.text = dateFormatter.format(Date(item.tanggal))
            val hasilBmi = HitungBmi.hitung(item)
            tvKategori.text = hasilBmi.kategori.toString()
            tvBmi.text = root.context.getString(R.string.bmi_x, hasilBmi.bmi)
            tvBerat.text = root.context.getString(R.string.berat_x, item.berat)
            tvTinggi.text = root.context.getString(R.string.tinggi_x, item.tinggi)
            val stringRes = if (item.isMale) R.string.pria else R.string.wanita
            tvGender.text = root.context.getString(stringRes)
        }
    }

    private val data = mutableListOf<BmiEntity>()

    fun updateData(newData: List<BmiEntity>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoriAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
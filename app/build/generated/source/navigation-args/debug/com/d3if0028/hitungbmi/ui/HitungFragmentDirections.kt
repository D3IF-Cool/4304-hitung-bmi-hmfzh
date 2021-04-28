package com.d3if0028.hitungbmi.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.d3if0028.hitungbmi.R
import com.d3if0028.hitungbmi.`data`.KategoriBmi
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class HitungFragmentDirections private constructor() {
  private data class ActionHitungFragmentToSaranFragment(
    public val kategori: KategoriBmi
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_hitungFragment_to_saranFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
      val result = Bundle()
      if (Parcelable::class.java.isAssignableFrom(KategoriBmi::class.java)) {
        result.putParcelable("kategori", this.kategori as Parcelable)
      } else if (Serializable::class.java.isAssignableFrom(KategoriBmi::class.java)) {
        result.putSerializable("kategori", this.kategori as Serializable)
      } else {
        throw UnsupportedOperationException(KategoriBmi::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      return result
    }
  }

  public companion object {
    public fun actionHitungFragmentToSaranFragment(kategori: KategoriBmi): NavDirections =
        ActionHitungFragmentToSaranFragment(kategori)

    public fun actionHitungFragmentToAboutFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_hitungFragment_to_aboutFragment)
  }
}

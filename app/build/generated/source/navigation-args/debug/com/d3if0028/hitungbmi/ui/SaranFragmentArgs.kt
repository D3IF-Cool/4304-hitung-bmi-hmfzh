package com.d3if0028.hitungbmi.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.d3if0028.hitungbmi.`data`.KategoriBmi
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class SaranFragmentArgs(
  public val kategori: KategoriBmi
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SaranFragmentArgs {
      bundle.setClassLoader(SaranFragmentArgs::class.java.classLoader)
      val __kategori : KategoriBmi?
      if (bundle.containsKey("kategori")) {
        if (Parcelable::class.java.isAssignableFrom(KategoriBmi::class.java) ||
            Serializable::class.java.isAssignableFrom(KategoriBmi::class.java)) {
          __kategori = bundle.get("kategori") as KategoriBmi?
        } else {
          throw UnsupportedOperationException(KategoriBmi::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__kategori == null) {
          throw IllegalArgumentException("Argument \"kategori\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"kategori\" is missing and does not have an android:defaultValue")
      }
      return SaranFragmentArgs(__kategori)
    }
  }
}

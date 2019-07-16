package marketingexe.com.br.carroskotlinapp.domain

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import java.lang.StringBuilder

class Carro(
    val modelo: String,
    val ano: Int,
    val marca: Marca,
    val motor: Motor,
    val preco: Float,
    val acessorios: List<Acessorio>,
    val imagem: Bitmap
) : Parcelable {
    fun getAcessoriosFormatted(): String {
        val aux = StringBuilder()

        for (acessorio in acessorios)
            aux.append("${acessorio.nome}(${acessorio.preco}),")

        return aux.trimEnd().trimEnd(',').toString()

    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readParcelable<Marca>(Marca::class.java.classLoader),
        source.readParcelable<Motor>(Motor::class.java.classLoader),
        source.readFloat(),
        source.createTypedArrayList(Acessorio.CREATOR),
        source.readParcelable<Bitmap>(Bitmap::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(modelo)
        writeInt(ano)
        writeParcelable(marca, 0)
        writeParcelable(motor, 0)
        writeFloat(preco)
        writeTypedList(acessorios)
        writeParcelable(imagem, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Carro> = object : Parcelable.Creator<Carro> {
            override fun createFromParcel(source: Parcel): Carro = Carro(source)
            override fun newArray(size: Int): Array<Carro?> = arrayOfNulls(size)
        }
        val CARROS = "carros"

    }



}

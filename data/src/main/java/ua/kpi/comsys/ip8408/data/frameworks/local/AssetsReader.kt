package ua.kpi.comsys.ip8408.data.frameworks.local

import android.content.Context
import android.graphics.BitmapFactory
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok

class AssetsReader(private val context: Context) {
    fun read(fileName: String) = try {
        val file = context.assets.open(fileName).bufferedReader()
        val data = file.readText()
        Ok(data)
    } catch (e: Exception) {
        Err(e)
    }

    fun open(fileName: String) = try {
        val img = context.assets.open(fileName)
        val bitmap = BitmapFactory.decodeStream(img)
        Ok(bitmap)
    } catch(e: Exception) {
        Err(e)
    }
}

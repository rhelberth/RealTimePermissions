package com.example.realtimepermission.kotlin

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import java.util.*

object Permissoes {

    fun validarPermissoes(permissoes: Array<String>, activity: Activity, requestCode: Int): Boolean {

        if (Build.VERSION.SDK_INT >= 23) {

            val listaPermissoes = ArrayList<String>()

            /*Percorre as permissões passadas,
            verificando uma a uma
            * se já tem a permissao liberada */
            for (permissao in permissoes) {
                val temPermissao = checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED

                if (!temPermissao)
                    listaPermissoes.add(permissao)
            }
            /*Caso a lista esteja vazia, não é necessário solicitar permissão*/
            if (listaPermissoes.isEmpty())
                return true

            val novasPermissoes = arrayOfNulls<String>(listaPermissoes.size)
            listaPermissoes.toArray(novasPermissoes)

            //Solicita permissão
            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode)
            return false
        }

        return true

    }

}

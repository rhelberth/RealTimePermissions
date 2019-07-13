package com.example.realtimepermission.kotlin

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimepermission.R

class MainActivity : AppCompatActivity() {


    private val permissoes = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Validar permissões
        Permissoes.validarPermissoes(permissoes, this, 1)

    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (permissaoResultado in grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao()
            }
        }

    }

    private fun alertaValidacaoPermissao() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permissões Negadas")
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões")
        builder.setCancelable(false)
        builder.setPositiveButton("Confirmar") { _, _ ->  finish()}

        val dialog = builder.create()
        dialog.show()

    }

}

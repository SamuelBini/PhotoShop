package fr.estia.bihar2122.dev_mobile.photoshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fr.estia.bihar2122.dev_mobile.photoshop.databinding.ActivityDetailsBinding

class Details : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  Récupérartion des données
        var annonce = intent.getStringArrayListExtra("product")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        binding.photo.setImageResource(annonce!!.get(0).toInt())

        binding.titrePhoto.setText(annonce!!.get(1))
        binding.prixPhoto.setText(annonce!!.get(2))

        binding.imageButton.setOnClickListener{
            finish()
        }

    }

}
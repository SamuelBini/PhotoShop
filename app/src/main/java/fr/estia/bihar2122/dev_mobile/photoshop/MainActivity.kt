package fr.estia.bihar2122.dev_mobile.photoshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import fr.estia.bihar2122.dev_mobile.photoshop.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    var products = mutableListOf<PhotoProduct>()
    var annonce : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        // on récupèere les images dans une liste de Int
        var imgArray = intArrayOf(
            R.drawable.photo_1,
            R.drawable.photo_2,
            R.drawable.photo_3,
            R.drawable.photo_4,
            R.drawable.photo_5,
            R.drawable.photo_6,
            R.drawable.photo_7,
            R.drawable.photo_8,
            R.drawable.photo_9,
            R.drawable.photo_10
        )

        //  Génération des prix et des titres des produits
        var prixImage = mutableListOf<Int>()
        var titleImage = mutableListOf<String>()
        for (i in 1..10){
            prixImage.add(
                Random.nextInt(100, 1000)
            )
            titleImage.add(
                "Produit $i"
            )
        }

        for(i in 0..9)
        {
            products.add(PhotoProduct(imgArray[i], titleImage[i], prixImage[i]))
        }

        //  Définir la 1ere image par défaut
        binding.photo.setImageResource(products[annonce].resource)


        //  Défilement des images
        binding.leftArrow.setOnClickListener{
            annonce = annonce - 1
            if(annonce < 0)
                annonce = 9
            displayProduct(annonce)

        }

        binding.rightArrow.setOnClickListener{
            annonce = annonce + 1
            if(annonce > 9)
                annonce = 0
            displayProduct(annonce)
        }


        //  Clic sur le bouton d'achat
        binding.buyButton.setOnClickListener{
            navigateToDetails(it)
        }


    }


    //  Fonction pour afficher les infos du produit sur la page
    fun displayProduct(i : Int) {
        binding.photo.setImageResource(products[i].resource)
        binding.titrePhoto.setText(products[i].title)
        binding.prixPhoto.setText("${products[i].price} €")
    }


    //  FOnction pour naviguer vers la page de détail et y envoyer les paramètres
    fun navigateToDetails(view: View) {
        val intent = Intent(this, Details::class.java)

        var toPush = ArrayList<String>()
        toPush.add(
            this.products[this.annonce].resource.toString()
        )
        toPush.add(
            this.products[this.annonce].title
        )
        toPush.add(
            this.products[this.annonce].price.toString()
        )

        intent.putStringArrayListExtra("product", toPush)
        startActivity(intent)
    }


}
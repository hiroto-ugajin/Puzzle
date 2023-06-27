package jp.kanoyastore.hiroto.ugajin.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import jp.kanoyastore.hiroto.ugajin.puzzle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val drawableArray = arrayOf(
        R.drawable.a0,
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
        R.drawable.a4,
        R.drawable.a5,
        R.drawable.a6,
        R.drawable.a7,
        R.drawable.a8,
        R.drawable.a9,
        R.drawable.a10,
        R.drawable.a11,
        R.drawable.a12,
        R.drawable.a13,
        R.drawable.a14,
        R.drawable.a15
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var shuffledDrawableArray = drawableArray.clone().apply {
            shuffle()
        }

        // カードの画像をセットする
        for (i in 0 until 16) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            val imageButton = findViewById<ImageButton>(buttonId)
            val letterImage = shuffledDrawableArray[i]
            imageButton.setImageResource(letterImage)

        }

        // カードがクリックされたときの処理
        for (i in 0 until 16) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            val imageButton = findViewById<ImageButton>(buttonId)

            imageButton.setOnClickListener {
                Log.d("TAG", "button${i}がタップされました")

            }
        }

    }
}
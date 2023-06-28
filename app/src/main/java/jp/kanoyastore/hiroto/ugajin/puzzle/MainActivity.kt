package jp.kanoyastore.hiroto.ugajin.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
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

                    val nextButtonId = resources.getIdentifier("button${i + 1}", "id", packageName)
                    val nextButton = findViewById<ImageButton>(nextButtonId)
                // タップされたボタンと次のボタンの画像リソースIDを取得
                    val tappedImageResourceId = shuffledDrawableArray[i]
                    val nextImageResourceId = shuffledDrawableArray[i + 1]
                    val foreImageResourceId = shuffledDrawableArray[i - 1]

                val foreButtonId = resources.getIdentifier("button${i - 1}", "id", packageName)
                val foreButton = findViewById<ImageButton>(foreButtonId)

                if (i <= 14 && nextImageResourceId == R.drawable.a15) {

                    // 画像リソースIDから画像を取得
                    val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                    val nextImage = resources.getDrawable(nextImageResourceId, null)

                    // 取得した画像を次のボタンに設定
                    nextButton.setImageDrawable(tappedImage)
                    //次のボタンの画像をタップしたボタンに設定
                    imageButton.setImageDrawable(nextImage)

//                   // 配列の要素の入れ替え

                        val temp = shuffledDrawableArray[i]
                       shuffledDrawableArray[i] = shuffledDrawableArray.getOrElse(i + 1) { temp }
//                      shuffledDrawableArray[i] = shuffledDrawableArray[R.drawable.a15]

                        shuffledDrawableArray[i + 1] = temp

                }

                if (1 <= i && foreImageResourceId == R.drawable.a15) {

                    val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                    val foreImage = resources.getDrawable(foreImageResourceId, null)

                    // 取得した画像を前のボタンに設定
                    foreButton.setImageDrawable(tappedImage)
                    //前のボタンの画像をタップしたボタンに設定
                    imageButton.setImageDrawable(foreImage)

                    // 配列の要素の入れ替え

                    val temp = shuffledDrawableArray[i]
                    shuffledDrawableArray[i] = shuffledDrawableArray.getOrElse(i - 1) { temp }
                    shuffledDrawableArray[i - 1] = temp



                }

            }
        }
    }
}
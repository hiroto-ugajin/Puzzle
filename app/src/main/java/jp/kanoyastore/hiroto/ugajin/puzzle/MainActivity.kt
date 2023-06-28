package jp.kanoyastore.hiroto.ugajin.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import jp.kanoyastore.hiroto.ugajin.puzzle.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

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

    private var isButtonsEnabled = true
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    fun disableAllImageButtons() {
        isButtonsEnabled = false
        for (i in 0 until 16) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            val imageButton = findViewById<ImageButton>(buttonId)
            imageButton.isEnabled = false
        }
    }

    fun enableAllImageButtons() {
        isButtonsEnabled = true
        for (i in 0 until 16) {
            val buttonId = resources.getIdentifier("button$i", "id", packageName)
            val imageButton = findViewById<ImageButton>(buttonId)
            imageButton.isEnabled = true
        }
    }

    fun delayEnableButtons() {
        coroutineScope.launch {
            delay(500) // 0.5秒待つ
            enableAllImageButtons()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() // Coroutineのキャンセル
    }

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

                if (isButtonsEnabled) {
                    disableAllImageButtons()
                    delayEnableButtons()
                }

                    val nextButtonId = resources.getIdentifier("button${i + 1}", "id", packageName)
                    val nextButton = findViewById<ImageButton>(nextButtonId)
                // タップされたボタンと次のボタンの画像リソースIDを取得
                    val tappedImageResourceId = shuffledDrawableArray[i]
                    val nextImageResourceId = shuffledDrawableArray[i + 1]
                    val foreImageResourceId = shuffledDrawableArray[i - 1]
//                    val upperImageResourceId = shuffledDrawableArray[i - 4]


                val foreButtonId = resources.getIdentifier("button${i - 1}", "id", packageName)
                val foreButton = findViewById<ImageButton>(foreButtonId)

//                val upperButtonId = resources.getIdentifier("button${i - 4}", "id", packageName)
//                val upperButton = findViewById<ImageButton>(upperButtonId)





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


                if (4 <= i) {

                        val upperButtonId =
                            resources.getIdentifier("button${i - 4}", "id", packageName)
                        val upperButton = findViewById<ImageButton>(upperButtonId)
                        val upperImageResourceId = shuffledDrawableArray[i - 4]

                        if (upperImageResourceId == R.drawable.a15) {
                            val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                            val upperImage = resources.getDrawable(upperImageResourceId, null)

                            // 取得した画像を前のボタンに設定
                            upperButton.setImageDrawable(tappedImage)
                            //前のボタンの画像をタップしたボタンに設定
                            imageButton.setImageDrawable(upperImage)

                            // 配列の要素の入れ替え
                            val temp = shuffledDrawableArray[i]
                            shuffledDrawableArray[i] =
                                shuffledDrawableArray.getOrElse(i - 4) { temp }
                            shuffledDrawableArray[i - 4] = temp
                        }
                }

                if (i <= 11) {

                    val belowButtonId =
                        resources.getIdentifier("button${i + 4}", "id", packageName)
                    val belowButton = findViewById<ImageButton>(belowButtonId)
                    val belowImageResourceId = shuffledDrawableArray[i + 4]

                    if (belowImageResourceId == R.drawable.a15) {
                        val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                        val belowImage = resources.getDrawable(belowImageResourceId, null)

                        // 取得した画像を前のボタンに設定
                        belowButton.setImageDrawable(tappedImage)
                        //前のボタンの画像をタップしたボタンに設定
                        imageButton.setImageDrawable(belowImage)

                        // 配列の要素の入れ替え
                        val temp = shuffledDrawableArray[i]
                        shuffledDrawableArray[i] =
                            shuffledDrawableArray.getOrElse(i + 4) { temp }
                        shuffledDrawableArray[i + 4] = temp
                    }
                }




            }
        }
    }
}
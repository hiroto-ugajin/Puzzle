package jp.kanoyastore.hiroto.ugajin.puzzle

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.FrameLayout
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
            delay(200) // 0.2秒待つ
            enableAllImageButtons()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() // Coroutineのキャンセル
    }

    @SuppressLint("WrongViewCast")
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

                val tappedImageResourceId = shuffledDrawableArray[i]

                if (i <= 14) {
                    val nextButtonId = resources.getIdentifier("button${i + 1}", "id", packageName)
                    val nextButton = findViewById<ImageButton>(nextButtonId)
                    val nextImageResourceId = shuffledDrawableArray[i + 1]

                    if (nextImageResourceId == R.drawable.a15) {

                        val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                        val nextImage = resources.getDrawable(nextImageResourceId, null)

                        nextButton.setImageDrawable(tappedImage)
                        imageButton.setImageDrawable(nextImage)

                        val temp = shuffledDrawableArray[i]
                        shuffledDrawableArray[i] = shuffledDrawableArray.getOrElse(i + 1) { temp }
                        shuffledDrawableArray[i + 1] = temp
                    }
                }

                if (1 <= i ) {

                    val foreImageResourceId = shuffledDrawableArray[i - 1]
                    val foreButtonId = resources.getIdentifier("button${i - 1}", "id", packageName)
                    val foreButton = findViewById<ImageButton>(foreButtonId)

                    if (foreImageResourceId == R.drawable.a15) {

                        val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                        val foreImage = resources.getDrawable(foreImageResourceId, null)

                        foreButton.setImageDrawable(tappedImage)
                        imageButton.setImageDrawable(foreImage)

                        val temp = shuffledDrawableArray[i]
                        shuffledDrawableArray[i] = shuffledDrawableArray.getOrElse(i - 1) { temp }
                        shuffledDrawableArray[i - 1] = temp
                    }
                }

                if (4 <= i && !(i == 9 || i == 10)) {

                        val upperButtonId =
                            resources.getIdentifier("button${i - 4}", "id", packageName)
                        val upperButton = findViewById<ImageButton>(upperButtonId)
                        val upperImageResourceId = shuffledDrawableArray[i - 4]

                        if (upperImageResourceId == R.drawable.a15) {
                            val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                            val upperImage = resources.getDrawable(upperImageResourceId, null)

                            upperButton.setImageDrawable(tappedImage)
                            imageButton.setImageDrawable(upperImage)

                            val temp = shuffledDrawableArray[i]
                            shuffledDrawableArray[i] =
                                shuffledDrawableArray.getOrElse(i - 4) { temp }
                            shuffledDrawableArray[i - 4] = temp
                        }
                }

                if (i <= 11 && !(i == 5 || i == 6)) {

                    val belowButtonId =
                        resources.getIdentifier("button${i + 4}", "id", packageName)
                    val belowButton = findViewById<ImageButton>(belowButtonId)
                    val belowImageResourceId = shuffledDrawableArray[i + 4]

                    if (belowImageResourceId == R.drawable.a15) {
                        val tappedImage = resources.getDrawable(tappedImageResourceId, null)
                        val belowImage = resources.getDrawable(belowImageResourceId, null)

                        belowButton.setImageDrawable(tappedImage)
                        imageButton.setImageDrawable(belowImage)

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
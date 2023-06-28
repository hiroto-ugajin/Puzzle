package jp.kanoyastore.hiroto.ugajin.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.kanoyastore.hiroto.ugajin.puzzle.databinding.ActivitySecondBinding

private lateinit var binding: ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val drawableResId1 = R.drawable.sston2
        binding.image1.setImageResource(drawableResId1)

        val drawableResId2 = R.drawable.sshaku2
        binding.image2.setImageResource(drawableResId2)

        binding.title.text = "将棋麻雀パズル"
        binding.title.textSize = 30f

        binding.comment1.text = "将棋の駒と麻雀の牌を絵柄にしたタイルの移動パズルです。" +
                "下記の２種類が完成パターンです。中央に黒い横棒のバリアが設定されています。" +
                "タイルはここを通過して移動することはできません。" +
                "完成に到達するとVictory Soundが流れます。" +
                "RESTARTボタンをタップするとゲームが再設定できますので、繰り返して楽しむことができます。"
    }
}
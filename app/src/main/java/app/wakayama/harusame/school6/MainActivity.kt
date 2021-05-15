package app.wakayama.harusame.school6

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var random = kotlin.random.Random
    val questionCount:Int = 4//クイズ数
    var questions = Array(4){it}//クイズ数の大きさの答えの配列を確保
    var point:Int = 0//点数
    var answerCount:Int = 0//答えの配列のインデックス
    var answer = true//素数かどうか

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //問題を作成
        setQuestion()
        numberLabelText.setTextColor(Color.parseColor("#00ffff"))
        numberLabelText.text = questions[0].toString()
        //ボタン処理
        maruButton.setOnClickListener {
            Log.d("debug","anscount is $answerCount")
            setAnswer()
            if (answer == true){
                point ++
            }
            changeQuestion()
        }
        batuButton.setOnClickListener {
            Log.d("debug","anscount is $answerCount")
            setAnswer()
            if(answer == false){
                point++
            }
            changeQuestion()
        }
    }
    fun setQuestion(){
        //問題を作成
        for (i in 0..questionCount-1){
            val number = random.nextInt(1000)
            questions[i] = number
            Log.d("debug",number.toString())
        }
    }
    fun setAnswer(){
        //正解をセット
        val number = questions[answerCount]
        Log.d("debug","number is $number")
        for(i in 2..(number-1)){
            if(number%i == 0){//素数でなければ
                answer = false
                break
            }
        }
    }
    fun changeQuestion(){
        if (answerCount == questionCount-1){//全問解いたなら点数表示して終わり
            Log.d("debug","final anscount is $answerCount")
            numberLabelText.text = "$point 点"
            numberLabelText.setTextColor(Color.parseColor("#ff0000"))
            point = 0
            answerCount = 0
        }else{//そうでなければ問題を進める
            answerCount++
            //数字を表示
            numberLabelText.text = questions[answerCount].toString()+""
            //文字色を青へ
            numberLabelText.setTextColor(Color.parseColor("#00ffff"))
        }
    }
}
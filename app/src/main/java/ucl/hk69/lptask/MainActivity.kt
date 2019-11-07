package ucl.hk69.lptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val tapTimes: Int = 10
    val tapText: String = "タップ！"
    val handler: Handler = Handler()

    var count: Int = 10
    var time: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonArray: Array<Button> = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)

        timer(period = 100) {
            handler.post {
                if(count < tapTimes) {
                    time += 0.1
                    textTime.text = "%.1f秒".format(time)
                }
            }
        }

        for(button in buttonArray){
            button.setOnClickListener {
                if(button.text.equals(tapText)){
                    count++
                    clean(button)
                    if(count < tapTimes) next(buttonArray[Random.nextInt(buttonArray.size)])
                }
            }
        }

        buttonStart.setOnClickListener {
            count = 0
            time = 0.0
            for (button in buttonArray) clean(button)
            textTime.text = time.toString()
            next(buttonArray[Random.nextInt(buttonArray.size)])
        }
    }

    fun clean(button: Button){
        button.text = ""
        button.setBackgroundResource(R.drawable.round_button_nomal)
    }

    fun next(button: Button){
        button.text = tapText
        button.setBackgroundResource(R.drawable.round_button_highlight)
    }
}

package cl.desafiolatam.plaplix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import cl.desafiolatam.plaplixtest.view.PhoneListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, PhoneListFragment.newInstance("", ""), "lista")
                    .commit()
        } else {
            supportFragmentManager.findFragmentByTag("lista")
        }
    }
}
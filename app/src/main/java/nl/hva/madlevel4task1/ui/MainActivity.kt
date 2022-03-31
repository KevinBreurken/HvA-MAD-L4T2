package nl.hva.madlevel4task1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nl.hva.madlevel4task1.R
import nl.hva.madlevel4task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
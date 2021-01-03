package by.itacademy.homework10

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework10.databinding.ActivityMainBinding
import by.itacademy.homework10.model.MusicModel
import by.itacademy.homework10.presentation.MainActivityViewModel
import by.itacademy.homework10.presentation.MusicTitleAdapter
import by.itacademy.homework10.presentation.PlayerService
import by.itacademy.homework10.presentation.ServiceActions

const val TAG = "Guv"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val titleAdapter by lazy { MusicTitleAdapter() }
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var player: ServiceActions? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            player = (service as PlayerService.PlayerBinder).getPlayerActions()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            player = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPermissions()
        initViewModel()
        prepareRecycler()
        initButtons()
        bindService(
                Intent(this, PlayerService::class.java),
                serviceConnection,
                0
        )
        startService(Intent(this@MainActivity, PlayerService::class.java))
    }

    override fun onResume() {
        super.onResume()
        mainActivityViewModel.getMusicData(this)
    }

    private fun getPermissions() {
        val permissionArrayList = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.FOREGROUND_SERVICE)
        permissionArrayList.forEach { item ->
            if (ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(item), 1000)
            }
        }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
        mainActivityViewModel.musicLiveData.observe(this, Observer<List<MusicModel>> { musicModel ->
            titleAdapter.updateAdapter(musicModel)
        })
    }

    private fun prepareRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = titleAdapter
        }
    }

    private fun initButtons() {
        with(binding) {
            playButton.setOnClickListener {
                player?.startMusic()
                Toast.makeText(this@MainActivity, "Play", Toast.LENGTH_SHORT).show()
            }
            stopButton.setOnClickListener {
                player?.stopMusic()
                Toast.makeText(this@MainActivity, "Stop", Toast.LENGTH_SHORT).show()
            }
            pauseButton.setOnClickListener {
                player?.pauseMusic()
                Toast.makeText(this@MainActivity, "Pause", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
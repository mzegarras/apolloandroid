package cloud.csonic.apollodemo

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View

import cloud.csonic.apollodemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

interface BaseActivity{
    fun showLoading()
    fun hideLoading()
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),BaseActivity {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)



        binding.fab.setOnClickListener { view ->

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()


            var id = findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id;



            Log.d(MainActivity::class.qualifiedName,"${findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id}---")

            Log.d(MainActivity::class.qualifiedName,"${findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.SecondFragment}")
            Log.d(MainActivity::class.qualifiedName,"${findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.FirstFragment}")


            if(id ==R.id.FirstFragment){
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
            }else{
                //navController.navigate(R.id.action_SecondFragment_to_FirstFragment)
                navController.popBackStack();
            }

        }



    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


    override fun showLoading() {
            val incLoading: View = binding.incLoading.rlaLoading
            incLoading.bringToFront()
            incLoading.visibility = View.VISIBLE



    }

    override fun hideLoading() {

            val incLoading: View = binding.incLoading.rlaLoading
            incLoading.bringToFront()
            incLoading.visibility = View.GONE

    }
}
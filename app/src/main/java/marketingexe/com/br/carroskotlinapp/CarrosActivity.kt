package marketingexe.com.br.carroskotlinapp

import adapter.CarrosAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_carros.*
import marketingexe.com.br.carroskotlinapp.data.Mock
import marketingexe.com.br.carroskotlinapp.domain.Carro

class CarrosActivity : AppCompatActivity() {

    val carros = ArrayList<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)

        carros.addAll(savedInstanceState
            ?.getParcelableArrayList(Carro.CARROS)
            ?:Mock().gerarCarro(resources))

        initRecycler()
    }

    private fun initRecycler(){
        rv_carros.setHasFixedSize(true)

        val mLayoutManager = LinearLayoutManager(this)
        rv_carros.layoutManager = mLayoutManager

        val divider = DividerItemDecoration(this,mLayoutManager.orientation)
        rv_carros.addItemDecoration(divider)

        val adapter = CarrosAdapter(this, carros)
        rv_carros.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList( Carro.CARROS, carros )
        super.onSaveInstanceState( outState )
    }
}

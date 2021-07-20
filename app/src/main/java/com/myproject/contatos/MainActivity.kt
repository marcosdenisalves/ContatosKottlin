package com.myproject.contatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myproject.contatos.DetailActivity.Companion.EXTRA_CONTACT
import com.myproject.contatos.controller.ContactAdapter
import com.myproject.contatos.interfaces.ClickItemContactListener
import com.myproject.contatos.models.Contact

class MainActivity : AppCompatActivity(), ClickItemContactListener {
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rvList)
    }
    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        iniDrawer()
        bindView()
        updateList()
    }

    private fun iniDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun bindView() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun updateList() {
        adapter.updateList(
            arrayListOf(
                Contact(
                    "Dênis Alves",
                    "(91) 98877-6655",
                    "img.png"
                ),
                Contact(
                    "Patricia Tereza",
                    "(91) 99988-7766",
                    "img.jpeg"
                )
            )
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                showToast("Exibindo item de menu 1")
                true
            }
            R.id.item_menu_2 -> {
                showToast("Exibindo item de menu 2 ")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}
/*
 * Copyright (C) 2018 by onlymash <im@mash.im>, All rights reserved
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package im.mash.moebooru.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import im.mash.moebooru.App.Companion.app
import im.mash.moebooru.R
import im.mash.moebooru.utils.Key

class SearchActivity : BaseActivity() {

    internal var widthScreen: Int = 0
    internal var toolbarHeight = 0
    internal var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moebooru)
        val metric: DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        widthScreen = metric.widthPixels
        val tv = TypedValue()
        if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }
        val bundle = intent.getBundleExtra(Key.BUNDLE)
        val searchFragment = SearchFragment()
        searchFragment.arguments = bundle
        if (savedInstanceState == null) {
            displayFragment(searchFragment)
        }
    }

    private fun displayFragment(fragment: ToolbarFragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_moebooru, fragment)
                .commitAllowingStateLoss()
    }

    internal fun setActionBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.action_grid -> {
                app.settings.gridModeString = Key.GRID_MODE_GRID
                menu?.findItem(R.id.action_grid)?.isChecked = true
            }
            R.id.action_staggered_grid -> {
                app.settings.gridModeString = Key.GRID_MODE_STAGGERED_GRID
                menu?.findItem(R.id.action_staggered_grid)?.isChecked = true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_search, menu)
        this.menu = menu
        setMenuOption()
        return true
    }

    internal fun setMenuOption() {
        when (app.settings.gridModeString) {
            Key.GRID_MODE_GRID -> menu?.findItem(R.id.action_grid)?.isChecked = true
            Key.GRID_MODE_STAGGERED_GRID -> menu?.findItem(R.id.action_staggered_grid)?.isChecked = true
        }
    }
}
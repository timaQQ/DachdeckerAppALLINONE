package de.timurg.dachdeckerappallinone.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.timurg.dachdeckerappallinone.domain.model.CListItem

class CalculationsData() {

    private val _items = MutableLiveData<List<CListItem>>()
    val items: LiveData<List<CListItem>>
        get() = _items

    val cOne = listOf("Fassade", "Steildach", "Flachdach")
    val cTwo = listOf("Einteilungen", "Materialbedarf", "Dachaufbauten")

    val calculationsList = listOf(
        CListItem(cOne[0], cTwo[0], "Doppeldeckung"),
        CListItem(cOne[0], cTwo[0], "Wabendeckung"),
        CListItem(cOne[1], cTwo[1], "Flächenziegel"),
        CListItem(cOne[1], cTwo[1], "Konterlattung"),


//            CListItem(categoryOne[], categoryTwo[], ""),
//            CListItem(categoryOne[], categoryTwo[], ""),
//            CListItem(categoryOne[], categoryTwo[], ""),
//            CListItem(categoryOne[], categoryTwo[], "")
    )

    fun getItem(){
        _items.value = calculationsList
    }
}
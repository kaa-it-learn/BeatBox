package ru.tvhelp.akruglov.beatbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import ru.tvhelp.akruglov.criminalintent.SingleFragmentActivity

class BeatBoxActivity : SingleFragmentActivity() {

    override fun createFragment() = BeatBoxFragment.newInstance()
}

package com.i4evercai.android.support.InputFilter

import android.text.InputFilter
import android.text.Spanned

/**
 * Created by Fitz on 2017/12/20 0020.
 */
class DecimalDigitsInputFilter : InputFilter {

    private val decimalDigits: Int

    constructor(decimalDigits: Int) {
        this.decimalDigits = decimalDigits
    }

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
        if (source!=null && dest!=null){
            var dotPos = -1
            val len = dest.length
            for (i in 0 until len) {
                val c = dest.get(i)
                if (c == '.' || c == ',') {
                    dotPos = i
                    break
                }
            }
            if (dotPos >= 0) {

                // protects against many dots
                if (source == "." || source == ",") {
                    return ""
                }
                // if the text is entered before the dot
                if (dend <= dotPos) {
                    return ""
                }
                if (len - dotPos > decimalDigits) {
                    return ""
                }
            }
        }


        return ""
    }
}
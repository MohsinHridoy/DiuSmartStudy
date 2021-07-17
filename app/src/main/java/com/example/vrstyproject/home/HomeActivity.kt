package com.example.vrstyproject.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.example.vrstyproject.R
import com.example.vrstyproject.home.adapter.BooksAdapter
import com.example.vrstyproject.home.adapter.HeaderBookItemAdapter
import com.example.vrstyproject.home.adapter.PdfBookAdapter
import com.example.vrstyproject.model.BookItem
import com.example.vrstyproject.model.HeaderBookItem
import com.example.vrstyproject.model.PdfModel
import com.example.vrstyproject.uploadbook.UploadBookActivity
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    private lateinit var mcontext: Context
    lateinit var itemPicker:RecyclerView

    private lateinit var headerBookItemAdapter: HeaderBookItemAdapter
    private lateinit var BookItemAdapter: PdfBookAdapter
    private lateinit var headerBook:ArrayList<HeaderBookItem>
    private lateinit var BookItem:ArrayList<BookItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initInstance()

        initClickListener();

    }

    private fun initClickListener() {

        open_book.setOnClickListener {
            startActivity(Intent(this@HomeActivity, UploadBookActivity::class.java))
        }

    }

    private fun initInstance() {
        mcontext = this
        headerBook= ArrayList()
        BookItem= ArrayList()
        itemPicker= RecyclerView(mcontext)

        val options= FirebaseRecyclerOptions.Builder<PdfModel>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("mydocuments"), PdfModel::class.java)
            .build()

        headerBookItemAdapter= HeaderBookItemAdapter(mcontext)
        BookItemAdapter=PdfBookAdapter(options)

        // val rvSections: RecyclerView = root.findViewById<View>(R.id.rvSections) as RecyclerView


        headerBook.add(HeaderBookItem(1, R.drawable.book1))
        headerBook.add(HeaderBookItem(2, R.drawable.book2))
        headerBook.add(HeaderBookItem(3, R.drawable.book3))
        headerBook.add(HeaderBookItem(4, R.drawable.book4))
        headerBook.add(HeaderBookItem(5, R.drawable.book5))
        headerBook.add(HeaderBookItem(6, R.drawable.book2))


      //  CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL);

        rvHeader.layoutManager = CarouselLayoutManager(RecyclerView.HORIZONTAL, true)
        (rvHeader.layoutManager as CarouselLayoutManager).setPostLayoutListener(
            CarouselZoomPostLayoutListener()
        )

        rvHeader.adapter = headerBookItemAdapter
       // rvHeader.addOnScrollListener(CenterScrollListener())


        headerBookItemAdapter.addAll(headerBook)

        addBookItem()
    }

    private fun addBookItem() {
       // BookItem.add(BookItem(1,R.drawable.book1,"Lion","Sabbir"))
       // BookItem.add(BookItem(2,R.drawable.book2,"Sabbir","Sabbir"))


        rvBooks.layoutManager=LinearLayoutManager(mcontext, RecyclerView.VERTICAL, false)
        rvBooks.adapter=BookItemAdapter
       // BookItemAdapter.addAll(BookItem)








    }


    override fun onStart() {
        super.onStart()
        BookItemAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        BookItemAdapter.stopListening()

    }

    fun onCurrentItemChanged(viewHolder: RecyclerView.ViewHolder?, position: Int) {
       // val pos: Int = itemPicker
       // val bookCover: HomeBookCover = booklist.get(pos)
       // val fileLoc: String = bookCover.getLocation()
        //Log.e("Position = ", fileLoc);
     //   mFileLoc = fileLoc
    }
}
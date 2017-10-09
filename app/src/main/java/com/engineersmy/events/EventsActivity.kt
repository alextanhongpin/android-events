package com.engineersmy.events

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

import com.fasterxml.jackson.module.kotlin.*
import java.util.*

class EventsActivity : AppCompatActivity() {

    // Define the class that represents the json data
    data class Event(
            val id: Int,
            val name: String)

    data class Events(
        val data: List<Event>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        val listView:ListView = findViewById(R.id.events_list) as ListView
        listView.adapter = ListEventsAdapter(this)


    }

    private class ListEventsAdapter(context: Context) : BaseAdapter() {
        private val mapper = jacksonObjectMapper()
        private val json = """[ {
          "id" : "1",
          "name" : "Blues"
        }, {
          "id" : "0",
          "name" : "Rock"
        } ]"""
//        val json = URL("https://events.engineers.my/public/v1/events").readText()
//        internal var sList = arrayOf("one", "two", "three", "four", "five")
        private val events: List<Event> = mapper.readValue(json)

//        events.forEach {
//            Log.i("events", it.name)
//        }
        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return events.size
        }

        override fun getItem(position: Int): Any {
            return events[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int,convertView: View?,parent: ViewGroup?): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.events_list,parent,false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }
            vh.label.text = events[position].name
            vh.id.text = events[position].id.toString()
            vh.date.text = Calendar.getInstance().get(Calendar.YEAR).toString()
            return view
        }
    }

    private class ListRowHolder(row: View?) {
        public val label: TextView
        public val id: TextView
        public val date: TextView

        init {
            this.label = row?.findViewById(R.id.eventName) as TextView
            this.id = row?.findViewById(R.id.eventId) as TextView
            this.date = row?.findViewById(R.id.eventDate) as TextView
        }
    }
}

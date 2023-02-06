package cloud.csonic.apollodemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cloud.csonic.apolloclients.GetAccountsByIdcQuery
import cloud.csonic.apollodemo.R

class AccountAdapter (private val dataSet: List<GetAccountsByIdcQuery.Account?>) : RecyclerView.Adapter<AccountAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtAccount: TextView
        val txtCurrency: TextView
        val txtAmount: TextView

        init {
            // Define click listener for the ViewHolder's View
            txtAccount = view.findViewById(R.id.txtAccount)
            txtCurrency = view.findViewById(R.id.txtCurrency)
            txtAmount = view.findViewById(R.id.txtAmout)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        dataSet[position].let {
            viewHolder.txtAccount.text = it?.number
            viewHolder.txtCurrency.text = it?.currency
            viewHolder.txtAmount.text = it?.balance.toString()
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}


package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Criterium;
import com.example.myapplication.utilities.Constants;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Aakash on 08/05/2019.
 */

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder> {

    Context ctx;
    List<Criterium> criterias;

    int index;

    public CriteriaAdapter(Context context, List<Criterium> objects) {
        this.ctx = context;
        this.criterias = objects;
    }

    @Override
    public void onBindViewHolder(CriteriaViewHolder criteriaViewHolder, int position) {


        criteriaViewHolder.txtCriteria.setTag(position);

        if (position == criterias.size() - 1)
            criteriaViewHolder.txtDivider.setVisibility(View.GONE);
        else
            criteriaViewHolder.txtDivider.setVisibility(View.VISIBLE);

        if (criterias.get(position).getType().equals(Constants.plain_text)) {
            criteriaViewHolder.txtCriteria.setText(criterias.get(position).getText());
        } else {
            String ss = criterias.get(position).getText();
            SpannableStringBuilder builder = getVariableString(ss, position);

            criteriaViewHolder.txtCriteria.setMovementMethod(LinkMovementMethod.getInstance());
            criteriaViewHolder.txtCriteria.setText(builder, TextView.BufferType.SPANNABLE);


        }
    }

    @Override
    public int getItemCount() {
        return criterias.size();
    }

    @Override
    public CriteriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_criteria, parent, false);
        return new CriteriaViewHolder(view);
    }

    class CriteriaViewHolder extends RecyclerView.ViewHolder {
        TextView txtCriteria, txtDivider;

        CriteriaViewHolder(View view) {
            super(view);
            txtCriteria = (TextView) view.findViewById(R.id.txtCriteria);
            txtDivider = (TextView) view.findViewById(R.id.txtDivider);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    SpannableStringBuilder getVariableString(String ss, int position) {


        index = 0;
        SpannableStringBuilder builder = new SpannableStringBuilder();

        do {
            int newIndex = ss.indexOf("$");

            builder.append(ss, index, newIndex);

            String subStr = ss.substring(newIndex, newIndex + 2);
            String displaytext = "", changedText = "";

            switch (subStr) {
                case "$1":

                    if (criterias.get(position).getVariable().get$1().getType().equals(Constants.indicator)) {

                        displaytext = "(" + criterias.get(position).getVariable().get$1().getDefaultValue() + ")";
                        changedText = "1" + criterias.get(position).getVariable().get$1().getDefaultValue() + "1";
                    } else {
                        displaytext = "(" + criterias.get(position).getVariable().get$1().getValues().get(0) + ")";
                        changedText = "1" + criterias.get(position).getVariable().get$1().getValues().get(0) + "1";
                    }

                    break;

                case "$2":

                    if (criterias.get(position).getVariable().get$2().getType().equals(Constants.indicator)) {
                        displaytext = "(" + criterias.get(position).getVariable().get$2().getDefaultValue() + ")";
                        changedText = "2" + criterias.get(position).getVariable().get$2().getDefaultValue() + "2";
                    } else {
                        displaytext = "(" + criterias.get(position).getVariable().get$2().getValues().get(0) + ")";
                        changedText = "2" + criterias.get(position).getVariable().get$2().getValues().get(0) + "2";
                    }

                    break;

                case "$3":

                    if (criterias.get(position).getVariable().get$3().getType().equals(Constants.indicator)) {
                        displaytext = "(" + criterias.get(position).getVariable().get$3().getDefaultValue() + ")";
                        changedText = "3" + criterias.get(position).getVariable().get$3().getDefaultValue() + "3";
                    } else {
                        displaytext = "(" + criterias.get(position).getVariable().get$3().getValues().get(0) + ")";
                        changedText = "3" + criterias.get(position).getVariable().get$3().getValues().get(0) + "3";
                    }

                    break;

                case "$4":

                    if (criterias.get(position).getVariable().get$4().getType().equals(Constants.indicator)) {
                        displaytext = "(" + criterias.get(position).getVariable().get$4().getDefaultValue() + ")";
                        changedText = "4" + criterias.get(position).getVariable().get$4().getDefaultValue() + "4";
                    } else {
                        displaytext = "(" + criterias.get(position).getVariable().get$4().getValues().get(0) + ")";
                        changedText = "4" + criterias.get(position).getVariable().get$4().getValues().get(0) + "4";
                    }

                    break;
            }

            ss = ss.replace(subStr, changedText);
            builder.append(displaytext);
            builder.setSpan(new ClickableSpan() {
                @Override

                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    TextView tv = (TextView) view;
                    Spanned s = (Spanned) tv.getText();

                    int ind = s.getSpanStart(this);

                    char c = criterias.get(pos).getText().charAt(ind);

                    Double[] values = new Double[]{};

                    switch (c) {
                        case '1':
                            if (criterias.get(pos).getVariable().get$1().getValues() != null)
                                values = criterias.get(pos).getVariable().get$1().getValues().toArray(values);

                            showDialog(criterias.get(pos).getVariable().get$1().getStudyType(), criterias.get(pos).getVariable().get$1().getType(), values);
                            break;
                        case '2':
                            if (criterias.get(pos).getVariable().get$2().getValues() != null)
                                values = criterias.get(pos).getVariable().get$2().getValues().toArray(values);

                            showDialog(criterias.get(pos).getVariable().get$2().getStudyType(), criterias.get(pos).getVariable().get$2().getType(), values);
                            break;
                        case '3':
                            if (criterias.get(pos).getVariable().get$3().getValues() != null)
                                values = criterias.get(pos).getVariable().get$3().getValues().toArray(values);

                            showDialog(criterias.get(pos).getVariable().get$3().getStudyType(), criterias.get(pos).getVariable().get$3().getType(), values);
                            break;
                        case '4':
                            if (criterias.get(pos).getVariable().get$4().getValues() != null)
                                values = criterias.get(pos).getVariable().get$4().getValues().toArray(values);

                            showDialog(criterias.get(pos).getVariable().get$4().getStudyType(), criterias.get(pos).getVariable().get$4().getType(), values);
                            break;
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(ctx.getResources().getColor(R.color.blue));
                    ds.setUnderlineText(true);
                }
            }, newIndex, newIndex + displaytext.length(), 0);

            index = newIndex + displaytext.length();

        } while (ss.contains("$"));


        builder.append(ss, index, ss.length());
        criterias.get(position).setText(ss);

        return builder;
    }


    void showDialog(String Component, String type, Double[] values) {

        Dialog dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_set_value);

        TextView txtComponent = dialog.findViewById(R.id.txtComponent);
        TextView txtType = dialog.findViewById(R.id.txtType);
        ListView listView = dialog.findViewById(R.id.listData);
        LinearLayout llEdit = dialog.findViewById(R.id.llEdit);

        if (type.equals(Constants.indicator)) {
            txtComponent.setVisibility(View.VISIBLE);
            txtComponent.setText(Component.toUpperCase());

            txtType.setText(R.string.set_param);
            listView.setVisibility(View.GONE);
            llEdit.setVisibility(View.VISIBLE);
        } else {
            txtComponent.setVisibility(View.GONE);

            txtType.setText(R.string.select_value);
            Arrays.sort(values);

            llEdit.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(ctx, android.R.layout.simple_list_item_1, values);
            listView.setAdapter(adapter);
        }

        dialog.show();

    }


}
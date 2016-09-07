package droid.simplegraph;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Rocka
 * E-mail：Rocka_mail@163.com
 * Time: 2016/3/18 14:36
 */
public class MainActivity extends Activity{

    private GraphView graphView;
    private List<DimensionTO> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        grades = new ArrayList<>();
        grades.add(new DimensionTO("defense" , 8));
        grades.add(new DimensionTO("attack" , 5));
        grades.add(new DimensionTO("conscious" , 10));
        grades.add(new DimensionTO("power" , 7));
        grades.add(new DimensionTO("purpose" , 10));

        graphView = (GraphView) findViewById(R.id.review_detail_gradeview);
        graphView.setGradeItem(grades);
    }
}

# RewardAnimation

一个简单的树状图制作，避免使用MPAndroidChart类似的增大APK包


### 整合

没有什么添加到你的项目


### 使用

``` java

 grades = new ArrayList<>();
 grades.add(new DimensionTO("defense" , 8));
 grades.add(new DimensionTO("attack" , 5));
 grades.add(new DimensionTO("conscious" , 10));
 grades.add(new DimensionTO("power" , 7));
 grades.add(new DimensionTO("purpose" , 10));
 graphView = (GraphView) findViewById(R.id.review_detail_gradeview);
 graphView.setGradeItem(grades);

``` 

### 预览

![image](https://github.com/KevinRocka/SimpleGraph/blob/master/SimpleGraph/screengif/simplegraph.png?raw=true)
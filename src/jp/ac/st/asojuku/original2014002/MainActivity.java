package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();


		// 登録ボタン変数にリスナーを登録する
		Button btnENTRY = (Button)findViewById(R.id.touroku);
		btnENTRY.setOnClickListener(this);

		// メンテボタン変数にリスナーを登録する
		Button btnMAINTE = (Button)findViewById(R.id.mente);
		btnMAINTE.setOnClickListener(this);

		//  一言チェックボタン変数にリスナーを登録する
		Button btnCHECK = (Button)findViewById(R.id.check);
		btnCHECK.setOnClickListener(this);

		//クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常終了
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = null;
		switch(v.getId()) {//メンテボタン
			case R.id.mente:
				intent = new Intent(MainActivity.this, MaintenanceActivity.class);
				startActivity(intent);
				break;

			case R.id.check://チェックボタン
				intent = new Intent(MainActivity.this, HitokotoActivity.class);
				startActivity(intent);
				break;

			case R.id.touroku: //登録ボタン
				EditText etv = (EditText)findViewById(R.id.txt1);
				String inputMsg = etv.getText().toString();

				break;
	}

	}
}

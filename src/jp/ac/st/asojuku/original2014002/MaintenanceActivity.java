package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


/**
 * @author student
 *
 */

public class MaintenanceActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{


	// SQLiteデータベース空間を操作するインスタンス変数を宣言
	SQLiteDatabase sdb = null;
	// MySQLiteOpenHelperを操作するインスタンス変数を宣言
	MySQLiteOpenHelper helper = null;

	// リストにて選択したHitokotoテーブルのレコードの「_id」カラム値
	int selectedID = -1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maintenance);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		// 各view部品を操作するidを取得
		Button btnDelete = (Button)findViewById(R.id.dalete);
		Button btnMainte_Back = (Button)findViewById(R.id.LinearLayout1);
		ListView lstHitokoto = (ListView)findViewById(R.id.list);

		// 各ButtonにOnClickListenerをセット
		btnDelete.setOnClickListener(this);
		btnMainte_Back.setOnClickListener(this);

		// ListViewにOnIemClickListenerをセット
		lstHitokoto.setOnItemClickListener(this);

		// ListViewにDBValuetoList(lstHitokoto);

		/**
		 * @parme AdapterView<？> parent クリックしたListView
		 * @parme View view クリックしたListViewの中の各行
		 * @parme int position 何行目をクリックしたか
		 * @parme long  viewid 未使用
		 */
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ

		// 前に選択中の行があれば、背景色お透明にする
		if(this.selectedID!=-1){
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
		}
		// 選択中の行の背景色をグレーにする
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		// 選択中のレコードを指し示す
		SQListeCursor cursor = (SQLiteCursor)parent.getItemAtPosition(position);
		//カーソルのレコードから、「_id」の値を取得して記憶
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
		//何行目を選択したかも記憶
		this.lastPosition = position;
	}


	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		switch(v.getId()) { //どのボタンが押されたか判定
		case R.id.btnDELETE: //削除ボタンが押された

			// 選択行があれば
			if(this.selectedID != -1){
				this.deleteFromHitokoto(this.selectedID);
				ListView lstHitokoto = (ListView)findViewById(R.id.LvHITOKOTO);
				// ListViewにDBをセット
				this.setDBValuetoList(lstHitokoto);
				// 選択行を忘れる
				this.selectedID = -1;
				this.lastPosition = -1;

			}

			else{
				// なければ、トースト (簡易メッセージ)を表示
				Tosdt.makeText(MaintenanceActivity.this,"削除する行を選んでください", Toast.LENGTH_SHROT).show();

			}
			break;
		case R.id.btnMAINTE_BACK: // 戻るボタンが押された
			// 今の画面Activityを消して、前の画面Activityに戻る
			finish();

		}
	}

}

/*
 * The MIT License (MIT)
 * Copyright (c) 2015 xinlishuo (http://www.xinlishuo.cn)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

@EBean)
public class TestViewHolder {
	
	@ViewById(R.id.Layout0)
	RelativeLayout	mLayout0;
	
	@ViewById(R.id.Back_but)
	ImageButton	mBack_but;
	
	@ViewById(R.id.Editor_but)
	ImageButton	mEditor_but;
	
	@ViewById(R.id.User_head)
	ImageView	mUser_head;
	
	@ViewById(R.id.User_name)
	EditText	mUser_name;
	
	@ViewById(R.id.User_work)
	TextView	mUser_work;
	
	@ViewById(R.id.User_age)
	TextView	mUser_age;
	
	@ViewById(R.id.Alter_password_but)
	Button	mAlter_password_but;
	
	@ViewById(R.id.About_app_but)
	Button	mAbout_app_but;
	
	@ViewById(R.id.Feed_back_but)
	Button	mFeed_back_but;
	
	@ViewById(R.id.Cancel_but)
	Button	mCancel_but;
	
	@Click(R.id.Back_but)
	public void onBack_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.Editor_but)
	public void onEditor_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.Alter_password_but)
	public void onAlter_password_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.About_app_but)
	public void onAbout_app_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.Feed_back_but)
	public void onFeed_back_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.Cancel_but)
	public void onCancel_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@TextChange(R.id.User_name)
	public void onTextChangedOnUser_name(CharSequence s, TextView hello, int before, int start, int count){
	
	}
	
		@AfterViews
	public void init(){
	//TODO do something
	}

	@SupposeBackground
	public void onRequestComplete(String json, String tag) {
	 super.onRequestComplete(json, tag);
	
	}
}
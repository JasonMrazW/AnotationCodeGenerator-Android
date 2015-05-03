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

@EActivity(R.layout.activity_verification_code.xml)
public class UserLoginActivity extends BaseActivity{
	
	@ViewById(R.id.LayoutMain)
	RelativeLayout	mLayoutMain;
	
	@ViewById(R.id.Back_but)
	ImageButton	mBack_but;
	
	@ViewById(R.id.Confirm_but)
	Button	mConfirm_but;
	
	@ViewById(R.id.Send_validate)
	Button	mSend_validate;
	
	@ViewById(R.id.Password_et)
	EditText	mPassword_et;
	
	@ViewById(R.id.PasswordDeleteIcon)
	ImageButton	mPasswordDeleteIcon;
	
	@ViewById(R.id.Validate_et)
	EditText	mValidate_et;
	
	@ViewById(R.id.ValidateDeleteIcon)
	ImageButton	mValidateDeleteIcon;
	
	@Click(R.id.Back_but)
	public void onBack_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.Confirm_but)
	public void onConfirm_butClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.Send_validate)
	public void onSend_validateClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.PasswordDeleteIcon)
	public void onPasswordDeleteIconClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@Click(R.id.ValidateDeleteIcon)
	public void onValidateDeleteIconClicked(View view){
	ViewClicker.ondelay(view);
	}
	
	@TextChange(R.id.Password_et)
	public void onTextChangedOnPassword_et(CharSequence s, TextView hello, int before, int start, int count){
	
	}
	
	@TextChange(R.id.Validate_et)
	public void onTextChangedOnValidate_et(CharSequence s, TextView hello, int before, int start, int count){
	
	}
	
	@SupposeBackground
	public void onRequestComplete(String json, String tag) {
	 super.onRequestComplete(json, tag);
	
	}
}
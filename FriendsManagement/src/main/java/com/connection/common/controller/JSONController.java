package com.connection.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.connection.common.model.Friends;
import com.connection.common.uitls.Tools;


@Controller
@RequestMapping("/api/createConnection")
public class JSONController {
	//http://localhost:8080/FriendsManagement/rest/api/createConnection?createFriendConnection&name=123@qq.com&name1=234@qq.com
	/**
	 * create connection
	 * @param name user email
	 * @param name1  friend email
	 * @return
	 */
	@RequestMapping(params = "createFriendConnection" ,method = RequestMethod.GET)
	public @ResponseBody
	Friends createConnection(@RequestParam String name,@RequestParam String name1) {
		Friends fs = new Friends();
		if(Tools.checkEmaile(name)&&Tools.checkEmaile(name1)){
			fs.setFlag("success");
			fs.setFriends(new String[] { name, name1 });
		}else{
			
			fs.setFlag("false");
			fs.setFriends(new String[] { name, name1 });
		}

		return fs;

	}
	/**
	 * get my friend list
	 * @param name eamil address
	 * @return
	 */
	//http://localhost:8080/FriendsManagement/rest/api/createConnection?getMyFriendList&name=123@qq.com
	@RequestMapping(params = "getMyFriendList",method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getFriendList(@RequestParam String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> friendsList = new ArrayList<String>();
		friendsList.add("andy@google.com");
		friendsList.add("joe@google.com");
		friendsList.add("danni@google.com");
		friendsList.add("frank@google.com");
		friendsList.add("davied@google.com");
		map.put("success",true);
		map.put("friends",friendsList);
		map.put("conut", friendsList.size());
		return map;

	}
	/**
	 * retrieve the common friends list
	 * @param name
	 * @param name1
	 * @return
	 */
	//http://localhost:8080/FriendsManagement/rest/api/createConnection?getCommonFriendList&name=123@qq.com&name1=234@qq.com
	@RequestMapping(params = "getCommonFriendList",method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommonFriendList(@RequestParam String name,@RequestParam String name1) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<String>> map1 = new HashMap<String, List<String>>();
		List<String> friendsList1 = new ArrayList<String>();
		friendsList1.add("andy@google.com");
		friendsList1.add("joe@google.com");
		friendsList1.add("danni@google.com");
		friendsList1.add("frank@google.com");
		friendsList1.add("davied@google.com");
		map1.put(name, friendsList1);
		Map<String, List<String>> map2 = new HashMap<String, List<String>>();
		List<String> friendsList2 = new ArrayList<String>();
		friendsList2.add("andy@google.com");
		friendsList2.add("joe@google.com");
		friendsList2.add("danni@google.com");
		friendsList2.add("frank@google.com");
		friendsList2.add("Aaron@google.com");
		map2.put(name1, friendsList2);
		List<String> sameList = Tools.getIntersection(friendsList1, friendsList2);
		map.put("success",true);
		map.put("friends",sameList);
		map.put("conut", sameList.size());
		return map;

	}
		/**
		 * update user email
		 * @param requestor
		 * @param target
		 * @return
		 */
		//http://localhost:8080/FriendsManagement/rest/api/createConnection?updUserInfo&requestor=123@qq.com&target=234@qq.com
		@RequestMapping(params = "updUserInfo",method = RequestMethod.POST)
		public @ResponseBody
		Map<String, Object> updUserInfo(@RequestParam String requestor,@RequestParam String target) {
			Map<String, Object> map = new HashMap<String, Object>();
			String message="";
			if(Tools.checkEmaile(requestor)&&Tools.checkEmaile(target)){
				message="update success!";
				map.put("success",true);
				map.put("message",message);
			}else{
				message="update false, please check input message!";
				map.put("success",false);
				map.put("message",message);
			}
			return map;

		}
		
		/**
		 * As a user, I need an API to block updates from an email address
		 * @param requestor
		 * @param target
		 * @param isfriend or not
		 * @return
		 */
		//http://localhost:8080/FriendsManagement/rest/api/createConnection?bindOrUnbind&requestor=123@qq.com&target=234@qq.com&flag=true
		@RequestMapping(params = "bindOrUnbind",method = RequestMethod.POST)
		public @ResponseBody
		Map<String, Object> bindOrUnbind(@RequestParam String requestor,@RequestParam String target,@RequestParam String flag) {

			Map<String, Object> map = new HashMap<String, Object>();
			String message="";
			if(flag!=null&& flag !=""&&"true".equals(flag)){
				message="unbind success!";
				map.put("message",message);
			}else{
				message="Be new friends!";
				map.put("message",message);
			}
			map.put("success",true);
			
			return map;

		}
		//As a user, I need an API to retrieve all email addresses that can receive updates from an  email address.
		/**
		 * As a user, I need an API to block updates from an email address
		 * @param requestor
		 * @param target
		 * @param isfriend or not
		 * @return
		 */
		//http://localhost:8080/FriendsManagement/rest/api/createConnection?getUpdEmails&sender=123@qq.com&text= Hello World ! 234@qq.com
		@RequestMapping(params = "getUpdEmails",method = RequestMethod.POST)
		public @ResponseBody
		Map<String, Object> getUpdEmails(@RequestParam String sender,@RequestParam String text) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> list = new ArrayList<String>();
			String msg ="";
			if(Tools.checkEmaile(sender)){
				list.add(sender);
				if(text!=null&&text!=""){
					String targetMail = text.substring(text.indexOf("!")+1).trim();
					if(targetMail.contains("@")){
						list.add(targetMail);
						map.put("recipients", list);
						map.put("success",true);
					}else{
						msg="please check target email address!";
						map.put("success",false);
						map.put("message",msg);
					}
				}else{
					msg="please check target email address!";
					map.put("success",false);
					map.put("message",msg);
				}
				
			}else{
				msg="please check sender email address!";
				map.put("success",false);
				map.put("message",msg);
			}
			
			return map;

		}
}
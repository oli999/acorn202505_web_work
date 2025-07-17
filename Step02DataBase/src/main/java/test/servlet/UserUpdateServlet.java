package test.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
/*
 *  enctype="multipart/form-data" 형식의 폼이 전송되었을때 처리할 서블릿 만들기 
 */
@WebServlet("/user/update")
@MultipartConfig(
	fileSizeThreshold = 1024*1024*10, //업로드 처리하기 위한 메모리 사이즈(10 Mega byte)
	maxFileSize = 1024*1024*50, //업로드되는 최대 파일 사이즈(50 Mega byte)
	maxRequestSize = 1024*1024*60 //이 요청의 최대 사이즈(60 Mega byte), 파일외의 다른 문자열도 전송되기 때문에
)
public class UserUpdateServlet extends HttpServlet{
	
	//업로드된 파일 저장경로를 저장할 필드 선언
	String fileLocation;
	
	//이 서블릿이 초기화되는 시점에 최초 1번 호출되는 메소드 
	@Override
	public void init() throws ServletException {
		//무언가 초기화 작업을 여기서 하면된다.
		ServletContext context = getServletContext();
		// web.xml 파일에 "fileLocation" 이라는 이름으로 저장된 정보 읽어와서 필드에 저장하기 
        fileLocation = context.getInitParameter("fileLocation");
	}
	
	
	// post 방식 전송되었을때 호출되는 메소드 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//폼 전송되는 내용 추출
		String userName=req.getParameter("userName");
		String email=req.getParameter("email");
		//파일데이터 (<input type="file" name="profileImage">)
		Part filePart=req.getPart("profileImage");
		//만일 업로드된 프로필 이미지가 있다면 (수정하지 않았으면 없다)
		if(filePart!=null && filePart.getSize() > 0) {
			//원본 파일의 이름 얻어내기
			String orgFileName=filePart.getSubmittedFileName();
			//파일명이 겹치지 않게 랜덤한 id 값 얻어내기
			String uid=UUID.randomUUID().toString();
			//저장될 파일명을 구성한다
			String saveFileName=uid+orgFileName;
			//저장할 파일의 경로 구성하기
			String filePath=fileLocation+"/"+saveFileName;
			/*
			 * 업로드된 파일은 임시 폴더에 임시 파일로 저장이 된다.
			 * 해당 파일에서 byte 알갱이를 읽어 들일수 있는 InputStream 객체를 얻어내서 
			 */
			InputStream is=filePart.getInputStream();
			// 원하는 목적지에 copy 를 해야 한다 
			Files.copy(is,  Paths.get(filePath));	
		}
		
		//리다일렉트 응답
		String cPath=req.getContextPath();
		resp.sendRedirect(cPath+"/user/info.jsp");
	}
}















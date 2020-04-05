package springbook.learningtest.templlate;

/**
 * 파일 안의 Line 읽어서 리턴해주는 인터페이스
 * @author 정훈
 * @Email manbalboy@hanmail.net
 * @discription
 * @date 2020. 4. 6.
 */
public interface LineCallback {
	Integer doSomethingWithLine (String line, Integer value);
}

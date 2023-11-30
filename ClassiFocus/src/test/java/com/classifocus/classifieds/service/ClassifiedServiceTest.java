package com.classifocus.classifieds.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.classifocus.classifieds.conf.security.UserService;
import com.classifocus.classifieds.dao.ClassifiedDao;
import com.classifocus.classifieds.document.Classified;
import com.classifocus.classifieds.mapper.ClassifiedMapper;
import com.classifocus.classifieds.model.GetClassifiedRequest;
import com.classifocus.classifieds.model.GetClassifiedResponse;
import com.classifocus.classifieds.model.SaveClassifiedRequest;
import com.classifocus.classifieds.model.SaveClassifiedResponse;
import com.classifocus.classifieds.model.UpdateClassifiedRequest;
import com.classifocus.classifieds.model.UpdateClassifiedResponse;
import com.classifocus.classifieds.validator.ClassifiedValidator;

@ExtendWith(MockitoExtension.class)
public class ClassifiedServiceTest {

	@Mock
	private ClassifiedDao classifiedDao;

	@Mock
	private ClassifiedValidator classifiedValidator;

	@Mock
	private ClassifiedMapper classifiedMapper;

	@Mock
	private UserService userService;

	@InjectMocks
	private ClassifiedService classifiedService;

	@Test
	public void testSaveClassified() throws Exception {

		SaveClassifiedRequest saveClassifiedRequest = new SaveClassifiedRequest(); 
		SaveClassifiedResponse saveClassifiedResponseExpected = prepareSaveClassifiedResponse();
		Classified classified = prepareClassified();

		doNothing().when(classifiedValidator).validateSaveClassifiedRequest(saveClassifiedRequest);
		given(classifiedMapper.toClassified(any(SaveClassifiedRequest.class))).willReturn(classified);
		given(classifiedDao.save(any(Classified.class))).willReturn(classified);
		given(classifiedMapper.toSaveClassifiedResponse(any(Classified.class))).willReturn(saveClassifiedResponseExpected);

		SaveClassifiedResponse saveClassifiedResponseActual = classifiedService.saveClassified(saveClassifiedRequest);

		assertThat(saveClassifiedResponseActual.getTitle()).isEqualTo(saveClassifiedResponseExpected.getTitle());

	}

	@Test
	public void testGetClassified() throws Exception {

		GetClassifiedRequest getClassifiedRequest = prepareGetClassifiedRequest();
		GetClassifiedResponse getClassifiedResponseExpected = prepareGetClassifiedResponse();
		Classified classified = prepareClassified();

		doNothing().when(classifiedValidator).validateGetClassifiedRequest(getClassifiedRequest);
		given(classifiedDao.findById(getClassifiedRequest.getClassifiedId())).willReturn(classified);
		given(classifiedMapper.toGetClassifiedResponse(classified)).willReturn(getClassifiedResponseExpected);

		GetClassifiedResponse getClassifiedResponseActual = classifiedService.getClassified(getClassifiedRequest);

		assertThat(getClassifiedResponseActual.getTitle()).isEqualTo(getClassifiedResponseExpected.getTitle());

	}

	@Test
	public void testUpdateClassified() throws Exception {

		UpdateClassifiedRequest updateClassifiedRequest = new UpdateClassifiedRequest();
		UpdateClassifiedResponse updateClassifiedResponseExpected = prepareUpdateClassifiedResponse();
		Classified classified = prepareClassified();

		doNothing().when(classifiedValidator).validateUpdateClassifiedRequest(updateClassifiedRequest);
		given(classifiedMapper.toClassified(any(UpdateClassifiedRequest.class))).willReturn(classified);
		given(classifiedDao.save(any(Classified.class))).willReturn(classified);
		given(classifiedMapper.toUpdateClassifiedResponse(any(Classified.class))).willReturn(updateClassifiedResponseExpected);

		UpdateClassifiedResponse updateClassifiedResponseActual = classifiedService.updateClassified(updateClassifiedRequest);

		assertThat(updateClassifiedResponseActual.getClassifiedId()).isEqualTo(updateClassifiedResponseExpected.getClassifiedId());

	}

	private SaveClassifiedResponse prepareSaveClassifiedResponse() {

		return SaveClassifiedResponse.builder().title("İlan").detail("Ev İlanı").build();
		
	}

	private Classified prepareClassified() {

		return Classified.builder().title("İlan").detail("Satılık Daire").build();
		
	}

	private GetClassifiedRequest prepareGetClassifiedRequest() {
		
		GetClassifiedRequest getClassifiedRequest = new GetClassifiedRequest();
		
		getClassifiedRequest.setClassifiedId("01GW845AQ7SRFM9CPSTR9DHTZM");
		
		return getClassifiedRequest;
		
	}
	
	private GetClassifiedResponse prepareGetClassifiedResponse() {
		return GetClassifiedResponse.builder().title("İlan").detail("Ev İlanı").build();
	}

	private UpdateClassifiedResponse prepareUpdateClassifiedResponse() {

		return UpdateClassifiedResponse.builder().classifiedId("01GW845AQ7SRFM9CPSTR9DHTZM").build();

	}

}

package com.r2s.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.r2s.entity.Author;
import com.r2s.enums.ErrorCodeEnum;
import com.r2s.model.ActionResult;
import com.r2s.dto.AuthorOutDto;
import com.r2s.model.AuthorModel;
import com.r2s.repository.AuthorRepository;
import com.r2s.service.AuthorService;
import jakarta.transaction.Transactional;

@Service
public class AuthorServiecImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;


	@Override
	public ActionResult getAllAuthor() {
		ActionResult result = new ActionResult();

		List<Author> authors = authorRepository.findAll();

		if (authors.isEmpty()) {
			result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
			return result;
		}

		List<AuthorModel> authorModels = authors.stream().map(AuthorModel::transform)
				.collect(Collectors.toList());

		AuthorOutDto outDto = new AuthorOutDto();
		outDto.setAuthors(authorModels);
		outDto.setTotal(authorModels.size());

		result.setData(outDto);

		return result;
	}

	@Override
	public ActionResult createAuthor(Author author) {
		ActionResult result = new ActionResult();

		Author authorTemp = authorRepository.save(author);

		if (authorTemp == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_CREATE_USER);
			return result;
		}

		result.setData(AuthorModel.transform(authorTemp));
		return result;
	}

	@Override
	public ActionResult updatetAuthor(Integer authorId, Author updatedAuthor) {
		ActionResult result = new ActionResult();

		Author authorTemp = authorRepository.getAuthorById(authorId);
		
		if (authorTemp == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_UPDATE_USER);
			return result;
		} else {
			authorTemp.setAuthorName(updatedAuthor.getAuthorName());
			authorTemp.setBiography(updatedAuthor.getBiography());
			
		}
		
		Author authorUpdate = authorRepository.save(authorTemp);

		if (authorUpdate == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_CREATE_USER);
			return result;
		}
		result.setData(AuthorModel.transform(authorUpdate));
		return result;
		
	}

	@Override
	public ActionResult getAuthorById(Integer authorId) {
		ActionResult result = new ActionResult();

		Author author = authorRepository.getAuthorById(authorId);
		if (author == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		}
		result.setData(AuthorModel.transform(author));
		return result;
	}

	@Override
	public ActionResult deleteAuthor(Integer authorId) {
		ActionResult result = new ActionResult();
		Author author = authorRepository.getAuthorById(authorId);
		if (author == null) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_USER);
			return result;
		} else {
			if (author.getBooks().isEmpty()) {
				authorRepository.delete(author);
				result.setData(new String("Delete success!"));
			}else {
				result.setErrorCodeEnum(ErrorCodeEnum.INVALID_DELETE);
				return result;
			}
			
		}
		return result;
	}

	@Override
	public ActionResult deleteAllBYIds(List<Integer> authorId) {
		ActionResult result = new ActionResult();
		try {
			authorRepository.deleteByIdIn(authorId);
			result.setData(new String("Delete success!"));
		} catch (Exception e) {
			result.setErrorCodeEnum(ErrorCodeEnum.INVALID_DELETE);
			return result;
		}
		
		return result;
	}

	

}

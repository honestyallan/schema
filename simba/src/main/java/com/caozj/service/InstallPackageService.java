package com.caozj.service;

import java.util.List;

import com.caozj.model.InstallPackage;

public interface InstallPackageService {

	void add(InstallPackage installPackage);

	void update(InstallPackage installPackage);

	void delete(String installVersion);

	List<InstallPackage> listAll();

	InstallPackage getNewest();

	int count();

	InstallPackage get(String installVersion);

	void updateNewest(String version);

	void batchDelete(List<String> installVersionList);
}

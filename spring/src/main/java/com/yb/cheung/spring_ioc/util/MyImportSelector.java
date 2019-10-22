package com.yb.cheung.spring_ioc.util;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String classNames[] = {"com.yb.cheung.spring_ioc.beans.Student"};
        return classNames;
    }
}

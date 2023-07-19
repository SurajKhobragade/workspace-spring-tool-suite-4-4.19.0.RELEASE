package org.sr.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Document(collection = "role")
@RequiredArgsConstructor
public class Role implements Serializable {

	@Id
	private String id;

	@NonNull
	private String name;
}

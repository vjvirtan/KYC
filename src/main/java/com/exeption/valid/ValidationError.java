package com.exeption.valid;

import java.io.*;

public record ValidationError<T extends Serializable, K extends Serializable>(String message, T input, K criteria) {

}

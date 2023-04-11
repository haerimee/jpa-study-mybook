import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import MyButton from "./MyButton";
import BookItem from "./BookItem";
import {call} from "../service/ApiService";
import SearchBar from "./SearchBar";

const BookList = ({ isReviewPage }) => {
    const [books, setBooks] = useState([]);
    const [reviews, setReviews] = useState([]);
    //const [keyword, setKeyword] = useState("");

    useEffect(() => {
        if(isReviewPage){
            handleReviewSearch();
        }
    }, [isReviewPage]);

    function handleBookSearch(keyword) {
        if(keyword){
            //setKeyword(keyword);
            let param = {keyword: keyword, start: 1}
            call("/api/book/search","GET", param)
                .then((res) => {
                    setBooks(res.items)
                });
        }else{
            alert("검색어를 입력하세요");
            return;
        }
    }

    function handleReviewSearch(){
        let param = {};
        call("/api/review/1","GET", param)
            .then((res) => {
                setReviews(res)
             });
    }

    return (
        <div className="BookList">
            <div className="menu_wrapper">
                {!isReviewPage && (
                    <div style={{width: '100%'}}>
                        <SearchBar onSearch={handleBookSearch} />
                    </div>
                )}
            </div>

            {books.length > 0 && books.map((it) => (
                <BookItem key={it.isbn} {...it} />
            ))}
            {!books.length > 0 && !isReviewPage && (
                <div>
                    검색어를 입력하여 도서를 검색해주세요.
                </div>
            )}
            {reviews.length > 0 && reviews.map((it) => (
                <BookItem key={it.isbn} {...it} />
            ))}
            {!reviews.length > 0 && isReviewPage && (
                <div>
                    독서 기록이 존재하지 않습니다.
                </div>
            )}
        </div>
    );
};

BookList.defaultProps = {
    bookList: [],
};

export default BookList;
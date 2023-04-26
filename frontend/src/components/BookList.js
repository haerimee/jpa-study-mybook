import React, {useCallback, useEffect, useRef, useState} from "react";
import BookItem from "./BookItem";
import {call} from "../service/ApiService";
import SearchBar from "./SearchBar";
import {useIntersectionObserver} from "../hooks/useIntersectionObserver";

const sortOptionList = [
    { value: "DESC", name: "최신순" },
    { value: "ASC", name: "오래된 순" }
];
const ControlMenu = React.memo(({ value, onChange, optionList }) => {
    return (
        <select className="ControlMenu" value={value} onChange={(e) => onChange(e.target.value)}>
            {optionList.map((it, idx) => (
                <option key={idx} value={it.value}>
                    {it.name}
                </option>
            ))}
        </select>
    )
});

const BookList = ({ isReviewPage }) => {
    const [books, setBooks] = useState([]);
    const [reviews, setReviews] = useState([]);
    const [sortType, setSortType] = useState("DESC");
    const pageSize = 10;

    useEffect(() => {
        if(isReviewPage){
            lastSort.current = sortType;
            handleReviewSearch(0, sortType);
        }
    }, [sortType]);

    const handleBookSearch = useCallback(
        (keyword, currentPage) => {
            if (keyword) {
                let param = { keyword: keyword, start: currentPage };
                call("/api/book/search", "GET", param).then((res) => {
                    setBooks(res.items);
                    lastCurrentPage.current = currentPage + pageSize
                });
            }
        },
        []
    );

    const loadMoreBooks = useCallback(
        (keyword, currentPage) => {
            if (keyword) {
                let param = { keyword: keyword, start: currentPage };
                call("/api/book/search", "GET", param).then((res) => {
                    setBooks((prevBooks) => [...prevBooks, ...res.items]);
                    lastCurrentPage.current = currentPage + pageSize
                });
            }
        },
        []
    );

    const handleReviewSearch = useCallback(
        (currentPage, sort) => {
        let param = {page: currentPage, sort: ["created_date", sort]};
        call("/api/review/all/1","GET", param)
            .then((res) => {
                setReviews(res.content);
                lastPage.current = currentPage + 1;
            });
        },[]
    )

    const loadMoreReviews = useCallback(
    (currentPage, sort) => {
                let param = { page: currentPage, sort: ["created_date", sort]};
                call("/api/review/all/1","GET", param)
                .then((res) => {
                    if (currentPage != 0) {
                        setReviews((prevReviews) => [...prevReviews, ...res.content]);
                    }
                    lastPage.current = currentPage + 1;
                });

        },
        []
    );

    const setObservationTarget = useIntersectionObserver(() => {
        if(!isReviewPage){
            loadMoreBooks(lastKeyword.current, lastCurrentPage.current);
        }else{
            loadMoreReviews(lastPage.current, lastSort.current);
        }

    });

    const lastKeyword = useRef("");
    const lastCurrentPage = useRef(1);
    const lastPage = useRef(0);
    const lastSort = useRef("DESC");

    function handleKeywordChange(keyword) {
        lastKeyword.current = keyword;
        handleBookSearch(keyword, 1);
    }

    return (
        <div className="BookList">
            <div className="menu_wrapper">
                {!isReviewPage ? (
                    <div style={{width: '100%'}}>
                        <SearchBar onSearch={handleKeywordChange} />
                    </div>
                    )
                    : (
                    <div className="left_col">
                        <ControlMenu value={sortType} onChange={setSortType} optionList={sortOptionList} />
                    </div>
                    )
                }
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
                <BookItem key={it.isbn} {...it} isReviewPage={true} />
            ))}
            {!reviews.length > 0 && isReviewPage && (
                <div>
                    독서 기록이 존재하지 않습니다.
                </div>
            )}
            <div ref={setObservationTarget}></div>
        </div>
    );
};

BookList.defaultProps = {
    bookList: [],
};

export default BookList;
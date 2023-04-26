import React, { useState } from "react";
import { FaSearch } from "react-icons/fa";

function SearchBar({ onSearch }) {
    const [keyword, setKeyword] = useState("");

    function handleSearch(e) {
        e.preventDefault();
        onSearch(keyword);
    }

    function handleKeywordChange(e) {
        setKeyword(e.target.value);
    }

    return (
        <form onSubmit={handleSearch}>
            <div className="SearchBar">
                <button type="submit">
                    <FaSearch />
                </button>
                <input
                    type="text"
                    placeholder="Search..."
                    value={keyword}
                    onChange={handleKeywordChange}
                />
            </div>
        </form>
    );
}

export default SearchBar;
